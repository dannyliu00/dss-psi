(function() {
    var unsavedChanges = sellInNamespace('sellIn.directives.unsavedChanges');

    function UnsavedChangesController($scope, $modalInstance) {
        $scope.saveChanges = function () {
            $modalInstance.close();
        };

        $scope.exitWithNoChanges = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    unsavedChanges.UnsavedChangesController = UnsavedChangesController;
})();