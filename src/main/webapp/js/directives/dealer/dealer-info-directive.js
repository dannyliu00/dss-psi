(function() {
    var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    function DealerInfoDirective() {
        return {
            restrict: 'E',
            scope: {dealer: '=dealer'},
            templateUrl: 'js/directives/dealer/dealer-info-template.html'
        };
    }

    dealerInfo.DealerInfoDirective = DealerInfoDirective;
})();