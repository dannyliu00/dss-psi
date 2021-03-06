(function () {
    var tabs = sellInNamespace('polaris.directives.tabs');

    function TabsController($scope, profileTabs, lastTab, translationPSI) {
                
        // Translate
        for (var i = 0, j =profileTabs.length; i < j; i++) {
        	profileTabs[i].name=translationPSI.getString(profileTabs[i].rawName);
        }
        
        $scope.tabs = profileTabs;

        var setActiveTab = function () {
            var status = lastTab.profilesTab;
            if (status === '') {
                status = 'current';
            }

            for (var i = 0, j = $scope.tabs.length; i < j; i++) {
                if (status === $scope.tabs[i].url) {
                    $scope.tabs[i].isActive = 'active';
                } else {
                    $scope.tabs[i].isActive = '';
                }
            }
        };

        $scope.translate = function(term) {
        	return translationPSI.getString(term);
        };
        $scope.showProfiles = function (url) {
            lastTab.changeProfilesTab(url);
            setActiveTab();
            $scope.$emit('tabClick', url);
        };

    }

    TabsController.prototype.setActiveTab = function () {
        this.setActiveTab();
    };

    tabs.TabsController = TabsController;
})();