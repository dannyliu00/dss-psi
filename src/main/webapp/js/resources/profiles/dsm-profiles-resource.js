(function() {
    var dsmProfiles = sellInNamespace('sellIn.resources.dsmProfiles');

    function DsmProfilesResource($resource, dsmCurrentProfilesUrl, dsmHistoryProfilesUrl) {
        this.resource = $resource(dsmCurrentProfilesUrl, {
                ran: function() {
                    // parameter added to URL to work around IE caching mechanism
                    return new Date().getTime();
                }}, {
                current: {method: 'GET', url: dsmCurrentProfilesUrl, isArray: true},
                history: {method: 'GET', url: dsmHistoryProfilesUrl, isArray: true}
            }
        );
    }

    DsmProfilesResource.prototype.queryCurrent = function(dsm) {
        return this.resource.current(dsm).$promise;
    };

    DsmProfilesResource.prototype.queryHistory = function(dsm) {
        return this.resource.history(dsm).$promise;
    };

    dsmProfiles.DsmProfilesResource = DsmProfilesResource;
})();
