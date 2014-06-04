(function() {
    var unsavedChanges = sellInNamespace('sellIn.directives.unsavedChanges');

    function UnsavedChangesController($scope, $modalInstance) {
        $scope.saveChanges = function () {
            $modalInstance.close('pass something from scope');
        };

        $scope.exitWithNoChanges = function () {
            $modalInstance.close('pass something from scope');
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    unsavedChanges.UnsavedChangesController = UnsavedChangesController;
})();