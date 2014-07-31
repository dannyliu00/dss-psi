describe('DsmProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dsmProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('queryCurrent', function() {
        it('returns a promise with a list of dealer profiles of a specified dsm and of a specified type', inject(function(dsmProfilesResource, dsmCurrentProfilesUrl) {
            var dsmId = 123456;
            var type = 'vehicle';
            var r = 12345;
            var dsm = {dsmId: dsmId, type: type, r: r};
            var expectedRequest = dsmCurrentProfilesUrl.replace(':dsmId', dsmId).replace(':type', type);
            expectedRequest += '?r=' + r;
            var expectedList = [{name: 'U.T. Victory Profile For A Dealer'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dsmProfilesResource.queryCurrent(dsm);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });

    describe('queryHistory', function() {
        it('returns a promise with a list of dealer profiles of a specified dsm and of a specified type', inject(function(dsmProfilesResource, dsmHistoryProfilesUrl) {
            var dsmId = 123456;
            var type = 'vehicle';
            var r = 12345;
            var dsm = {dsmId: dsmId, type: type, r: r};
            var expectedRequest = dsmHistoryProfilesUrl.replace(':dsmId', dsmId).replace(':type', type);
            expectedRequest += '?r=' + r;
            var expectedList = [{name: 'U.T. Victory Profile For A Dealer'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dsmProfilesResource.queryHistory(dsm);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
