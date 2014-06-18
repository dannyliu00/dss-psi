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
                sumSegmentTotal(os.segmentName, os.id, osActual);
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
                    if(orderSegment.profileId === periodId) {
	                    actQty = actQty + orderSegment.actual;
                    }
	            }
	            $scope.profile.periods[j].actual = actQty;
	    	}
	    	$scope.dirtyIndicator = $scope.dirtyIndicator + 1;
	    };

        function sumSegmentTotal(segmentName, osId, newValue) {
            var segment = getSegment(segmentName);
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                var orderSegment = segment.orderSegments[i];
                if(orderSegment.id === osId) {
                    orderSegment.actual = newValue;
                }
                total = total + parseInt(orderSegment.actual);
            }
            segment.actual = total;
        }

        function getSegment(name) {
            for(var i=0; i < $scope.segments.length; i++) {
                if($scope.segments[i].name === name) {
                    return $scope.segments[i];
                }
            }
        }
    }

    dealerProfiles.DealerProfileDirectiveController = DealerProfileDirectiveController;
})();