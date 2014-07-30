(function () {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    function CommonHeaderController ($scope, appTitle) {
        $scope.appTitle = appTitle;
    }

    commonHeader.CommonHeaderController = CommonHeaderController;
})();