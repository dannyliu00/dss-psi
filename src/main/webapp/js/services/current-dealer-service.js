(function () {
    angular.module('sellIn.services.currentdealer', [])
        .factory('currentDealer', function currentDealerFactory() {

            var currentDealer = {};

            currentDealer.dealerId = '';

            currentDealer.changeDealerId = function (dealerId) {
                currentDealer.dealerId = dealerId;
            };

            return currentDealer;
        });
})();