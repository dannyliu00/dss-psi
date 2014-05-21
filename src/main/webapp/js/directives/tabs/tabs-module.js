(function() {
    var tabs = sellInNamespace('polaris.directives.tabs');

    angular.module('polaris.directives.tabs', [])
        .directive('tabs', tabs.ApplicationTabs)
        .controller('tabsController', tabs.TabsController)
        .constant('pageTabs', [{name: 'Current Profile', url: 'profiles', isActive: 'active'}])
})();