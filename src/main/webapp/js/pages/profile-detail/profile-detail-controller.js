(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    function ProfileController($scope, $routeParams, $location, dealerResource, dealerProfileResource, orderSegmentResourceMapper) {
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
                $scope.recGrandTotal = getRecGrandTotal();
                $scope.actualGrandTotal = $scope.getActualGrandTotal();
        });

        $scope.toSummary = function(dealerId) {
            $location.path('#/dealerSummary/' + dealerId);
        };

        function getRecGrandTotal() {
            var total = 0;
            for(var i=0; i < $scope.profile.segments.length; i++) {
                total = total + getRecSegmentTotal($scope.profile.segments[i]);
            }

            return total;
        }

        $scope.getActualGrandTotal = function() {
            var total = 0;
            for(var i=0; i < $scope.profile.segments.length; i++) {
                total = total + getActualSegmentTotal($scope.profile.segments[i]);
            }

            return total;
        };

        function getRecSegmentTotal(segment) {
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                var orderSegment = segment.orderSegments[i];
                getComplianceValues(orderSegment);
                total = total + orderSegment.recommendedQty;
            }

            return total;
        }

        function getComplianceValues(orderSegment) {
            var rQty = 0;
            var minQty = 0;
            var maxQty = 0;

            for(var i=0; i < orderSegment.quantities.length; i++) {
                rQty = rQty + orderSegment.quantities[i].recommended;
                minQty = minQty + orderSegment.quantities[i].minimum;
                maxQty = maxQty + orderSegment.quantities[i].maximum;
            }

            orderSegment.recommendedQty = rQty;
            orderSegment.minimumQty = minQty;
            orderSegment.maximumQty = maxQty;
        }

        function getActualSegmentTotal(segment) {
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                var orderSegment = segment.orderSegments[i];
                orderSegment.actualQty = getActualValues(orderSegment);
                total = total + orderSegment.actualQty;
            }

            return total;
        }

        function getActualValues(orderSegment) {
            var total = 0;
            for(var i=0; i < orderSegment.quantities.length; i++) {
                var qty = orderSegment.quantities[i].actual;
                total = total + qty;
            }

            return total;
        }

    }

    profile.ProfileController = ProfileController;
})();