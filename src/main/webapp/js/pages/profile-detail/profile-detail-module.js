(function() {
    var profile = sellInNamespace('sellIn.pages.profile');

    angular.module('sellIn.pages.profile', ['sellIn.resources.dealerProfiles', 'sellIn.resources.dealer'])
        .controller('profileController', profile.ProfileController);
})();