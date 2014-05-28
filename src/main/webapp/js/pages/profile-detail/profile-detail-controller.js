(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    function ProfileController($scope, $routeParams, $location, dealerResource, dealerProfileResource) {
        var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        var profile = {profileId: 1234};
        dealerProfileResource.get(profile).then(function(returnedProfile) {
            $scope.profile = returnedProfile;
            $scope.segments = returnedProfile.segments;

            var orderSegments = [];
            for(var i = 0; i < returnedProfile.segments.length; i++) {
                var os = returnedProfile.segments[i].orderSegments;
                for(var j = 0; j < os.length; j++) {
                    orderSegments.push(os[j]);
                }
            }
            $scope.orderSegments = orderSegments;
        });

        $scope.toSummary = function(dealerId) {
            $location.path('#/dealerSummary/:dealerId');
        };

//        function getRecGrandTotal() {
//            var total = 0;
//            for(var i=0; i < $scope.profile.segments.length; i++) {
//                total = total + getRecSegmentTotal($scope.profile.segments[i]);
//            }
//
//            return total;
//        }

//        $scope.getActualGrandTotal = function() {
//            var total = 0;
//            for(var i=0; i < $scope.profile.segments.length; i++) {
//                total = total + getActualSegmentTotal($scope.profile.segments[i]);
//            }
//
//            return total;
//        };

//        function getRecSegmentTotal(segment) {
//            var total = 0;
//            for(var i=0; i < segment.orderSegments.length; i++) {
//                total = total + segment.orderSegments[i].recommendedQty;
//            }
//
//            return total;
//        }

//        function getActualSegmentTotal(segment) {
//            var total = 0;
//            for(var i=0; i < segment.orderSegments.length; i++) {
//                total = total + segment.orderSegments[i].actualQty;
//            }
//
//            return total;
//        }

        
//        $scope.recGrandTotal = getRecGrandTotal();
//        $scope.actualGrandTotal = $scope.getActualGrandTotal();
    }

    profile.ProfileController = ProfileController;
})();