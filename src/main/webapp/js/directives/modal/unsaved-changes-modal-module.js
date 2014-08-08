(function () {
    var unsavedChanges = sellInNamespace('sellIn.directives.unsavedChanges');

    angular.module('sellIn.directives.unsavedChanges', ['ngRoute', 'ui.bootstrap'])
        .controller('unsavedChangesController', unsavedChanges.UnsavedChangesController)
    ;
})();