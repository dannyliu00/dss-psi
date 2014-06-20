(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function DealerProfileDirectiveController($scope, DTOptionsBuilder, $routeParams, dealerResource,
    		dealerProfileResource, orderSegmentResourceMapper) {
    	
    	this.orderSegmentResourceMapper = orderSegmentResourceMapper;
    	
    	$scope.dirtyIndicator = 0;
    	
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
                $scope.recGrandTotal = $scope.profile.recommended;
                $scope.actualGrandTotal = $scope.getActualGrandTotal();
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
        
        $scope.getActualGrandTotal = function() {
	    	for(var j=0; j < $scope.profile.periods.length; j++) {
                var periodId = $scope.profile.periods[j].id;
	            var actQty = 0;
	            for(var i=0; i < $scope.orderSegments.length; i++) {
	                var orderSegment = $scope.orderSegments[i];
                    if(orderSegment.periodId === periodId) {
	                    actQty = actQty + orderSegment.actual;
                    }
	            }
	            $scope.profile.periods[j].actual = actQty;
	    	}
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
            var listCheck = $scope.segments[i].subSegments.indexOf(subSegment);
            if(listCheck !== -1) {
                return $scope.segments[i];
                i = $scope.segments.length;
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