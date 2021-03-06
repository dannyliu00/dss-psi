(function () {
    var tabs = sellInNamespace('polaris.directives.tabs');

    angular.module('polaris.directives.tabs', ['sellIn.services.lasttab','sellIn.services.translation'])
        .directive('tabs', tabs.ApplicationTabs)
        .controller('tabsController', tabs.TabsController)
        .constant('profileTabs', [
            {rawName: 'Current Profiles', url: 'current', isActive: 'active'},
            {rawName: 'Profiles History', url: 'history', isActive: ''}
        ]);
})();