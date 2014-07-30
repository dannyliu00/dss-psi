(function() {
    var mainMenu = sellInNamespace('polaris.directives.menu');

    function MainMenuDirective() {
        return {
            restrict: 'E',
            controller: mainMenu.MainMenuController,
            templateUrl: 'js/directives/menu/main-menu-rsm-template.html'
        }
    }

    mainMenu.MainMenuDirective = MainMenuDirective;
})();