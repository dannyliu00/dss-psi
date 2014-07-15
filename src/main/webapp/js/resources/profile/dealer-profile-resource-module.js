(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    angular.module('sellIn.resources.dealerProfile', ['ngResource'])
        .service('dealerProfileResource', dealerProfile.DealerProfileResource)
        .service('orderSegmentResourceMapper', dealerProfile.OrderSegmentResourceMapper)
        .constant('profileUrl', '/dss-psi/webapi/profile/:profileId/:dealerId')
        .constant('profileSaveUrl', '/dss-psi/webapi/profile/save')
        .constant('profileSubmitUrl', '/dss-psi/webapi/profile/submit')
        .constant('profileDsmSaveUrl','/dss-psi/webapi/dsm/profile/save')
        .constant('profileSendBackUrl','/dss-psi/webapi/dsm/profile/toDealer')
        .constant('profileApproveWChangesUrl', '/dss-psi/webapi/dsm/profile/approveWChanges')
        .constant('profileApproveRequestedUrl', '/dss-psi/webapi/dsm/profile/approveRequested')
        .constant('profileSubmitExceptionUrl', '/dss-psi/webapi/dsm/profile/approveException');
}());