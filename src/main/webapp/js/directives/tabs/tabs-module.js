(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    angular.module('polaris.directives.tabs', [])
        .directive('tabs', tabs.ApplicationTabs)
        .controller('tabsController', tabs.TabsController)
        .constant('pageTabs', [
            {name: 'Current Profiles', url: 'current', isActive: 'active'},
            {name: 'Profiles History', url: 'history', isActive: ''}]);
})();