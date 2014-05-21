(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    function ApplicationTabs() {
        return {
            restrict: 'E',
            controller: tabs.TabsController,
            templateUrl: 'js/directives/tabs/tabs-template.html'
        };
    }

    tabs.ApplicationTabs = ApplicationTabs;
})();