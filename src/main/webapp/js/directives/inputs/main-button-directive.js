(function() {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirective() {
    	
        return {
            restrict: 'E',
            controller: mainButton.MainButtonDirectiveController,
            templateUrl: 'js/directives/inputs/main-button-template.html'
        };
    }

    mainButton.MainButtonDirective = MainButtonDirective;
})();