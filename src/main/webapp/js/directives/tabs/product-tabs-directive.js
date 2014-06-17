(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsDirective() {
        return {
            restrict: 'E',
            controller: productTabs.ProductTabsController,
            templateUrl: 'js/directives/tabs/product-tabs-template.html'
        };
    }

    productTabs.ProductTabsDirective = ProductTabsDirective;
})();