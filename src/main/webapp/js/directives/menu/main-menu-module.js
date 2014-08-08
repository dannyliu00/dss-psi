(function () {
    var mainMenu = sellInNamespace('polaris.directives.menu');

    angular.module('polaris.directives.menu', [
        'sellIn.resources.role',
        'sellIn.resources.menu',
        'sellIn.resources.attribute'])
        .directive('mainMenu', mainMenu.MainMenuDirective)
        .controller('mainMenuController', mainMenu.MainMenuController);
})();