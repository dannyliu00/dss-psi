(function() {
    var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    function DealerInfoDirective() {
        return {
            restrict: 'E',
            controller: 'dealerInfoController',
            templateUrl: 'js/directives/dealer/dealer-info-template.html'
        };
    }

    dealerInfo.DealerInfoDirective = DealerInfoDirective;
})();