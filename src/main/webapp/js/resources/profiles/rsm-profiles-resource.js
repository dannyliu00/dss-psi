(function () {
    var rsmProfiles = sellInNamespace('sellIn.resources.rsmProfiles');

    function RsmProfilesResource($resource, rsmCurrentProfilesUrl, rsmHistoryProfilesUrl) {
        this.resource = $resource(rsmCurrentProfilesUrl, {}, {
                current: {method: 'GET', url: rsmCurrentProfilesUrl, isArray: true},
                history: {method: 'GET', url: rsmHistoryProfilesUrl, isArray: true}
            }
        );

    }

    RsmProfilesResource.prototype.queryCurrent = function (rsm) {
        return this.resource.current(rsm).$promise;
    };

    RsmProfilesResource.prototype.queryHistory = function (rsm) {
        return this.resource.history(rsm).$promise;
    };

    rsmProfiles.RsmProfilesResource = RsmProfilesResource;
})();