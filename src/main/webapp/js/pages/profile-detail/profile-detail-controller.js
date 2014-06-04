(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    function ProfileController($scope, $routeParams, $location, dealerResource, dealerProfileResource, orderSegmentResourceMapper) {
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

                var orderSegments = [];
                for (var i = 0; i < returnedProfile.segments.length; i++) {
                    var os = returnedProfile.segments[i].orderSegments;
                    for (var j = 0; j < os.length; j++) {
                        orderSegments.push(orderSegmentResourceMapper.mapFromRest(os[j], returnedProfile.segments[i]));
                    }
                }
                $scope.orderSegments = orderSegments;
            })
            .then(function() {
                $scope.recGrandTotal = $scope.profile.recommended;
                $scope.actualGrandTotal = $scope.getActualGrandTotal();
        });

	    $scope.getActualGrandTotal = function() {
	    	for(var j=0; j < $scope.profile.periods.length; j++) {
	            var actQty = 0;
	            for(var i=0; i < $scope.orderSegments.length; i++) {
	                var orderSegment = $scope.orderSegments[i];
	                actQty = actQty + orderSegment.quantities[j].actual;
	            }
	            $scope.profile.periods[j].actual = actQty;
	    	}
	    	$scope.dirtyIndicator = $scope.dirtyIndicator + 1;
	    };
	    
        $scope.toSummary = function(dealerId) {
            $location.path('#/dealerSummary/' + dealerId);
        };

    }

    profile.ProfileController = ProfileController;
})();