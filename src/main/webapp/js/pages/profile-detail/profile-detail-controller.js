(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    function ProfileController($scope, $routeParams, dealerResource, dealerProfilesResource) {
        var dealer = {dealerId: $routeParams.dealerId};
        dealerResource.get(dealer).then(function(returnedDealer) {
            $scope.dealer = returnedDealer;
        });

        var profile = {profileId: $routeParams.profileId};
        dealerProfilesResource.get(profile).then(function(returnedProfile) {
            $scope.profile = returnedProfile;
        });
    }

    profile.ProfileController = ProfileController;
})();