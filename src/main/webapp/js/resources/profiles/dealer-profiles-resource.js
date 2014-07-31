(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    function DealerProfilesResource($resource, currentProfilesUrl, historyProfilesUrl) {
        this.resource = $resource(currentProfilesUrl, {}, {
                current: {method: 'GET', url: currentProfilesUrl, isArray: true},
                history: {method: 'GET', url: historyProfilesUrl, isArray: true}
            }
        );
    }

    DealerProfilesResource.prototype.queryCurrent = function(profile) {
        return this.resource.current(profile).$promise;
    };

    DealerProfilesResource.prototype.queryHistory = function(profile) {
        return this.resource.history(profile).$promise;
    };
    
    dealerProfiles.DealerProfilesResource = DealerProfilesResource;
})();
