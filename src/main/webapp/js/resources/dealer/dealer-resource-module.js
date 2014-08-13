(function () {
    var dealer = sellInNamespace('sellIn.resources.dealer');

    angular.module('sellIn.resources.dealer', ['ngResource'])
        .service('dealerResource', dealer.DealerResource)
        .constant('dealerUrl', '/dss-psi/webapi/dealer/:type')
        .constant('nonDealerUrl', '/dss-psi/webapi/dealer/:type/:dealerId')
})();
