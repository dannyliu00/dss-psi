(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    function ProfileController($scope, $routeParams, dealerResource, dealerProfilesResource) {
        $scope.dealer = {dealerId: 2021900, name: 'ENGELHART MOTORSPORTS', city: 'MADISON', state: 'WI', zip: '53713', dsm: 'JESS PFEIFER'};
        var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
//            $scope.dealer = returnedDealer;
        });

        var profile = {profileId: $routeParams.profileId};
        dealerProfilesResource.get(profile).then(function(returnedProfile) {
//            $scope.profile = returnedProfile;
        });

        var cruiserOrderSegments = [
            {segmentName: 'Cruiser', name: 'Judge', recommendedQty: 0, actualQty: 0},
            {segmentName: 'Cruiser', name: 'Vegas 8-Ball', recommendedQty: 1, actualQty: 0},
            {segmentName: 'Cruiser', name: 'High-Ball', recommendedQty: 1, actualQty: 0},
            {segmentName: 'Cruiser', name: 'Gunner', recommendedQty: 0, actualQty: 0},
            {segmentName: 'Cruiser', name: 'Boardwalk', recommendedQty: 1, actualQty: 0},
            {segmentName: 'Cruiser', name: 'Fat Tire', recommendedQty: 1, actualQty: 0}
        ];

        var baggerOrderSegments = [
            {segmentName: 'Bagger', name: 'Cross Country', recommendedQty: 2, actualQty: 0},
            {segmentName: 'Bagger', name: 'Custom Cross Country', recommendedQty: 1, actualQty: 0},
            {segmentName: 'Bagger', name: 'Value Bagger', recommendedQty: 1, actualQty: 0},
            {segmentName: 'Bagger', name: 'Cross Roads', recommendedQty: 0, actualQty: 0}
        ];

        var touringOrderSegments = [
            {segmentName: 'Touring', name: 'Cross Country Tour', recommendedQty: 2, actualQty: 0},
            {segmentName: 'Touring', name: 'Vision', recommendedQty: 1, actualQty: 0}
        ];

        var segments = [
            {name: 'Cruiser', recommendedQty: 4, orderSegments: cruiserOrderSegments},
            {name: 'Bagger', recommendedQty: 4, orderSegments: baggerOrderSegments},
            {name: 'Touring', recommendedQty: 3, orderSegments: touringOrderSegments}
        ];
        $scope.profile = {profileId: 1234, name: 'Victory Inventory Profile 04/30/14', modifiedDate: '04/30/14', status: 'Not Started', segments: segments};

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
                total = total + segment.orderSegments[i].recommendedQty;
            }

            return total;
        }

        function getActualSegmentTotal(segment) {
            var total = 0;
            for(var i=0; i < segment.orderSegments.length; i++) {
                total = total + segment.orderSegments[i].actualQty;
            }

            return total;
        }

        $scope.recGrandTotal = getRecGrandTotal();
        $scope.actualGrandTotal = $scope.getActualGrandTotal();
    }

    profile.ProfileController = ProfileController;
})();