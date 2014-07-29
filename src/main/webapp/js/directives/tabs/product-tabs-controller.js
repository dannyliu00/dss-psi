(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope) {

        $scope.loadTabContent = function(tab) {
            $scope.$emit('productTabClick', tab);
        };

    }

    productTabs.ProductTabsController = ProductTabsController;
})();