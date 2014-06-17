(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope, productTabs) {
        this.scope = $scope;
        this.scope.tabs = productTabs;
    }

    productTabs.ProductTabsController = ProductTabsController;
    })();