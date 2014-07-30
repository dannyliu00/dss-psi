(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder, $routeParams, dealerResource,
    		dealerProfileResource, orderSegmentResourceMapper,appRoleResource, lastTab) {
    	
    	var self=this;

    	this.initialOrderSegments=null;

    	// Change control
    	$scope.isDirty=function() {
    		return !angular.equals(self.initialOrderSegments, $scope.orderSegments);
    	};
    	
    	$scope.resetChanges = function() {
    		self.initialOrderSegments = angular.copy($scope.orderSegments);
    	};
    	
        $scope.addEmail = function(email) {
        	$scope.dealerEmail = email;
        };
    	
    	appRoleResource.get().then(function(role) {
            $scope.role = role;
    		}).then(function(role) {
    			if($scope.role.rsm === true) {
    				$scope.authLevel = 'adminQty';
    			} else if ($scope.role.dsm === true) {
    				$scope.authLevel = 'dsmQty';
    			} else {
    				$scope.authLevel = 'actual';
    			}
    		});

    	this.orderSegmentResourceMapper = orderSegmentResourceMapper;

	    var dealer = {dealerId: $routeParams.dealerId, type: $routeParams.type};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        var profile = {profileId: $routeParams.profileId,dealerId: $routeParams.dealerId};
        dealerProfileResource.get(profile)
            .then(function(returnedProfile) {
            	
                $scope.profile = returnedProfile;
                $scope.segments = returnedProfile.segments; 

                for(var i = 0; i < returnedProfile.orderSegments.length; i++) {
                	var seg = returnedProfile.orderSegments[i];
                	
                	if($scope.authLevel === 'adminQty') {
                		if((seg.adminQty === null || seg.adminQty <= -1) && (seg.dsmQty > -1 && seg.dsmQty !== null)) {
                			seg.adminQty = seg.dsmQty;
                		} else if((seg.adminQty === null || seg.adminQty <= -1) && (seg.dsmQty <= -1 || seg.dsmQty === null)) {
                			seg.adminQty = seg.actual;
                			seg.dsmQty = seg.actual;
                		}
                	} else if($scope.authLevel === 'dsmQty' && (seg.dsmQty <= -1 || seg.dsmQty === null)) {
                		seg.dsmQty = seg.actual;
                	}
                }
                $scope.orderSegments = returnedProfile.orderSegments;
                $scope.distinctOS = findDistinctOSes($scope.orderSegments);
                
                // Remember the original data.
                $scope.resetChanges();

            })
            .then(function() {
                $scope.recommendedGrandTotal = $scope.getRecTotals();

        });

        function findDistinctOSes(data) {
            var keys = [];

            keys.push(data[0].name);
            if(data.length > 1) {
                for(var i = 1; i < data.length; i++) {
                    var newName = data[i].name;
                    if(keys.indexOf(newName) == -1) {
                        keys.push(newName);
                    }
                }
            }

            return keys;
        }
        
        $scope.isEditable = function() {
        	var status = $scope.profile.status;
        	if(lastTab.profilesTab === 'current') {
	        	if($scope.authLevel === 'adminQty' && status === 'EXCEPTION REQUESTED') {
	        		return true;
	        	} else if($scope.authLevel === 'dsmQty' && (status === 'PENDING' || status === 'RETURNED TO DSM')) {
	        		return true;
	        	} else if ($scope.authLevel === 'actual' && (status === 'NOT STARTED' || status === 'RETURNED TO DEALER' || status === 'IN PROGRESS')) {
	        		return true;
	        	}
        	}
        	return false;
        };

        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(25)
            .withBootstrap();

        $scope.getRecTotals = function() {
        	var totalRecQty = 0;
	    	for(var j=0; j < $scope.profile.periods.length; j++) {
                var periodCode = $scope.profile.periods[j].code;
	            var recQty = 0, recMin = 0, recMax = 0;
	            for(var i=0; i < $scope.orderSegments.length; i++) {
	            	var orderSegment = $scope.orderSegments[i];
	            	if(($scope.profile.type === "motorcycle") && (orderSegment.subSegment !== null)) {
	            		recSegmentTotal(orderSegment.subSegment);
	            	}
                    if(orderSegment.periodCode === periodCode) {
	                    recQty = recQty + orderSegment.recommended;
	                    recMin = recMin + orderSegment.recMinimum;
	                    recMax = recMax + orderSegment.recMaximum;
                    }
	            }
	            totalRecQty = totalRecQty + recQty;
	            $scope.profile.periods[j].recommended = recQty;
	            $scope.profile.periods[j].recMinimum = recMin;
	            $scope.profile.periods[j].recMaximum = recMax;
	    	}

	    	return totalRecQty;
	    };

	    function recSegmentTotal(sub) {
            var segment = getSegment(sub);
            var total = 0;
            for(var i=0; i < $scope.orderSegments.length; i++) {
            	var checkList = segment.subSegments.indexOf($scope.orderSegments[i].subSegment);
                if(checkList !== -1) {
                total = total + parseInt($scope.orderSegments[i].recommended);
                }
            segment.recommended = total;
            }
        }

        function getSegment(subSegment) {
        	for (var i=0; i<$scope.segments.length; i++) {
        		var listCheck = $scope.segments[i].subSegments.indexOf(subSegment);
        		if(listCheck !== -1) {
        			return $scope.segments[i];
        			i = $scope.segments.length;
        		}
        	}
        }
        
        // Calculate the total actuals for a period
        $scope.getPeriodTotal = function(periodCode) {
        	var actQty = 0;
        	var level = $scope.authLevel;
        	
            for(var i=0; i < $scope.orderSegments.length; i++) {
                var orderSegment = $scope.orderSegments[i];
                if(orderSegment.periodCode === periodCode) {
                	// Check if numeric
                	if((orderSegment[level]*1).toString()===orderSegment[level].toString()) {
                		
                		var actual = parseInt(orderSegment[level]);
                		if(actual>=0) {
                			actQty = actQty + actual;
                		}
                	}
                }
            }
            return actQty;
        };
		
		
		// Calculate total for a super segment
        $scope.getSegmentActualTotal = function(segment) {
            var level = $scope.authLevel;
        	var total = 0;
            var count = 0;
            
            for(var i=0; i < $scope.orderSegments.length; i++) {
            	
            	var checkList = segment.subSegments.indexOf($scope.orderSegments[i].subSegment);
                if(checkList !== -1) {
                	var orderSegmentVal = $scope.orderSegments[i][level];
                	
            		if((orderSegmentVal*1).toString()===orderSegmentVal.toString()) {
                		
                		var actual = parseInt(orderSegmentVal);
            			total += actual;
                		if(actual>0) {
                			count++;
                		}
                	}
            	}
            }
            return {actualTotal: total, orderSegmentCount:count};
        };
        
        // Get GrandTotal for all super segments
		$scope.getSuperSegmentGrandTotal = function() {
		     var total = 0;
		     for (var i=0; i<$scope.segments.length; i++) {
		    	 total+=$scope.getSegmentActualTotal($scope.segments[i]).actualTotal;
		     }
		     return total;
		};        
		

        function getSegment(subSegment) {
        	for (var i=0; i<$scope.segments.length; i++) {
        		var listCheck = $scope.segments[i].subSegments.indexOf(subSegment);
        		if(listCheck !== -1) {
        			return $scope.segments[i];
        			i = $scope.segments.length;
        		}
        	}
        }

        $scope.segName = function(sub) {
        	var nameCheck = 0;
        	var segmentName = "";
        	for (var i=0; i<$scope.segments.length; i++) {
        		nameCheck = $scope.segments[i].subSegments;
        		if(nameCheck.indexOf(sub) !== -1) {
        			segmentName = $scope.segments[i].name;
        			i = $scope.segments.length;
        			}
        	}
        	return segmentName;
        };

    }
    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();
