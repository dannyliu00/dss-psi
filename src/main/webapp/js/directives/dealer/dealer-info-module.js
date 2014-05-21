(function() {
    var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    angular.module('sellIn.directives.dealerInfo', [])
        .directive('dealerInfo', dealerInfo.DealerInfoDirective);
})();