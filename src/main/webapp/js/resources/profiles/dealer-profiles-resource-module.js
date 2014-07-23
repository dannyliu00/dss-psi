(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    angular.module('sellIn.resources.dealerProfiles', ['ngResource'])
        .service('dealerProfilesResource', dealerProfiles.DealerProfilesResource)
        .constant('currentProfilesUrl', '/dss-psi/webapi/profiles/:dealerId/current')
        .constant('historyProfilesUrl', '/dss-psi/webapi/profiles/:dealerId/history');
})();