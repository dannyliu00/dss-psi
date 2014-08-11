(function () {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    angular.module('sellIn.directives.mainbutton', ['ngRoute', 'ui.bootstrap', 'sellIn.routing.paths', 'sellIn.services.lasttab', 'sellIn.resources.dealerProfiles'])
        .controller('mainButtonDirectiveController', mainButton.MainButtonDirectiveController)
        .directive('mainButton', mainButton.MainButtonDirective);
})();