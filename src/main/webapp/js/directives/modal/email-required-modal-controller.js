(function () {
    var emailRequired = sellInNamespace('sellIn.directives.emailrequired');

    function EmailRequiredController($scope, $modalInstance) {

        $scope.closeModal = function () {
            $modalInstance.close();
        };
    }

    emailRequired.EmailRequiredController = EmailRequiredController;
})();