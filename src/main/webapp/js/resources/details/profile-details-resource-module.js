(function() {
    var dealerProfileDetails = sellInNamespace('sellIn.resources.dealerProfileDetails');

    angular.module('sellIn.resources.dealerProfileDetails', ['ngResource'])
        .service('dealerProfileDetailsResource', dealerProfileDetails.DealerProfileDetailsResource)
        ;
})();