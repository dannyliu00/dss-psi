(function () {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    function CommonHeaderController ($scope, appTitle) {
        this.scope = $scope;
        this.scope.appTitle = appTitle;
    }

    commonHeader.CommonHeaderController = CommonHeaderController;
})();