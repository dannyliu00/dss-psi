(function() {
    var dealerProfileDetails = sellInNamespace('sellIn.resources.dealerProfileDetails');

    angular.module('sellIn.resources.dealerProfileDetails', ['ngResource'])
        .service('dealerProfileDetailsResource', dealerProfileDetails.DealerProfileDetailsResource)
        .constant('detailsSaveUrl', '/dss-psi/webapi/details/save')
        .constant('detailsSubmitUrl', '/dss-psi/webapi/details/submit');
})();