(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    angular.module('sellIn.resources.dealerProfiles', ['ngResource'])
        .service('dealerProfilesResource', dealerProfiles.DealerProfilesResource)
        .constant('profilesUrl', '/dealers/:dealerId/profiles/:profileId');
}());