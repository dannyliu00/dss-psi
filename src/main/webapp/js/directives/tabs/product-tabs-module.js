(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    angular.module('sellIn.directives.producttabs', [])
    	.directive('productTabs',productTabs.ProductTabsDirective)
        .controller('productTabsController', productTabs.ProductTabsController);
})();