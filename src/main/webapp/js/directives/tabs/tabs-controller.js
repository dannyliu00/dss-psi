(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    function TabsController($scope, pageTabs) {
        this.scope = $scope;
        this.scope.tabs = pageTabs;
    }

    tabs.TabsController = TabsController;
})();