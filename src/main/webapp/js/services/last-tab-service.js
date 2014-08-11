(function () {
    angular.module('sellIn.services.lasttab', [])
        .factory('lastTab', function lastTabFactory() {

            var lastTab = {};

            lastTab.productTab = '';
            lastTab.changeProductTab = function (type) {
                lastTab.productTab = type;
            };

            lastTab.profilesTab = '';
            lastTab.changeProfilesTab = function (status) {
                lastTab.profilesTab = status;
            };

            return lastTab;
        });
})();