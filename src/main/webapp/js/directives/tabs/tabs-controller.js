(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    function TabsController($scope, pageTabs) {
        this.scope = $scope;
        this.scope.tabs = pageTabs;

        $scope.showProfiles = function(url) {
            $scope.$emit('tabClick', url);
        };


    }

    tabs.TabsController = TabsController;
})();