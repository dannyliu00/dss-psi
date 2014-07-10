(function() {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonDirective() {
    	
        return {
            restrict: 'E',
            controller: dsmButton.DsmButtonController,
            templateUrl: 'js/directives/inputs/dsm-button-template.html'
        };
    }

    dsmButton.DsmButtonDirective = DsmButtonDirective;
})();