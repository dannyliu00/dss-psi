(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    angular.module('sellIn.resources.dealerProfile', ['ngResource'])
        .service('dealerProfileResource', dealerProfile.DealerProfileResource)
        .constant('profileUrl', '/dss-psi/webapi/profile/:profileId');
}());