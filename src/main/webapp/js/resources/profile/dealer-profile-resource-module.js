(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    angular.module('sellIn.resources.dealerProfile', ['ngResource'])
        .service('dealerProfileResource', dealerProfile.DealerProfileResource)
        .service('orderSegmentResourceMapper', dealerProfile.OrderSegmentResourceMapper)
        .constant('profileUrl', '/dss-psi/webapi/profile/:profileId/:dealerId')
        .constant('profileUrl', '/dss-psi/webapi/profile/:profileId/save')
        .constant('profileUrl', '/dss-psi/webapi/profile/:profileId/submit');
}());