(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder, $routeParams, dealerResource,
    		dealerProfileResource, orderSegmentResourceMapper) {
    	
    	$scope.dirtyIndicator = 0;
    	
    	this.orderSegmentResourceMapper = orderSegmentResourceMapper;
    	
    	var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        var profile = {profileId: $routeParams.profileId};
        dealerProfileResource.get(profile)
            .then(function(returnedProfile) {
                $scope.profile = returnedProfile;
                $scope.segments = returnedProfile.segments;
                $scope.orderSegments = returnedProfile.orderSegments;
                $scope.distinctOS = findDistinctOSes($scope.orderSegments);
            })
            .then(function() {
                $scope.actualGrandTotal = $scope.getActualGrandTotal();
                $scope.recommendedGrandTotal = $scope.getRecTotals();
        });

        function findDistinctOSes(data) {
            var keys = [];

            if(data.length > 1) {
                keys.push(data[0].name);

                for(var i = 1; i < data.length; i++) {
                    var newName = data[i].name;
                    if(keys.indexOf(newName) == -1) {
                        keys.push(newName);
                    }
                }
            }

            return keys;
        }

        $scope.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withBootstrap();
        
        $scope.dealerEmail = "";
        
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
	    	$scope.dirtyIndicator = $scope.dirtyIndicator + 1;
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
          
        $scope.getActualGrandTotal = function() {
        	var totalQty = 0;
	    	for(var j=0; j < $scope.profile.periods.length; j++) {
                var periodCode = $scope.profile.periods[j].code;
	            var actQty = 0;    
	            for(var i=0; i < $scope.orderSegments.length; i++) {
	                var orderSegment = $scope.orderSegments[i];
                    if(orderSegment.periodCode === periodCode) {
	                    actQty = actQty + orderSegment.actual;
                    }
	            }
	            totalQty = totalQty + actQty;
	            $scope.profile.periods[j].actual = actQty;
	    	}
	    	return totalQty;
	    	$scope.dirtyIndicator = $scope.dirtyIndicator + 1;
	    };
	    
		$scope.sumActualValues = function() {
		     var total = 0;
		     for(var i = 0; i < $scope.orderSegments.length; i++) {
		    	 var os = $scope.orderSegments[i];
		         var osActual = angular.isNumber(os.actual) ? parseInt(os.actual) : 0;
		         total = total + osActual;
		         if(os.subSegment !== null) {
		              sumSegmentTotal(os.subSegment);
		            }
		         }
		     $scope.actualGrandTotal = total;
		     $scope.dirtyIndicator = $scope.dirtyIndicator + 1;
		};
		
        function sumSegmentTotal(sub) {
            var segment = getSegment(sub);
            var total = 0;
            for(var i=0; i < $scope.orderSegments.length; i++) {
            	var checkList = segment.subSegments.indexOf($scope.orderSegments[i].subSegment);
                if(checkList !== -1) {
                	total = total + parseInt($scope.orderSegments[i].actual);
                	}
            segment.actual = total;
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
        
        $scope.segmentTotalOS = function(segment) {
        	var totalOS = 0;
        	for (var i=0; i<$scope.orderSegments.length; i++) {
        		if(segment.subSegments.indexOf($scope.orderSegments[i].subSegment) !== -1) {
        			if($scope.orderSegments[i].actual > 0) {
        				totalOS += totalOS;
        			}
        		}	
        	}
        	return totalOS;
        };
        
        $scope.updateSegmentTotal = function(subSegment) {
        	var segment = $scope.segName(subSegment);
        	var segmentTotal = 0;
           	for (var i=0; i<$scope.orderSegments.length; i++) {
        		if(segment.subSegments.indexOf($scope.orderSegments[i].subSegment) !== -1) {
        			segmentTotal =  $scope.orderSegments[i].actual + segmentTotal;
        			}
        		}
           	$scope.segment.acutal = segmentTotal;
        };
    }
    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();