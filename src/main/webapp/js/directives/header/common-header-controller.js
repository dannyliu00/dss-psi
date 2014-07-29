(function () {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    function CommonHeaderController ($scope, appTitle, menuResource, appRoleResource, appAttributeResource) {
        $scope.appTitle = appTitle;
        
        appRoleResource.get().then(function(roleData) {
        	$scope.isSalesman = roleData.salesman;
        	$scope.isDealer = roleData.dealer;
        	$scope.isDIS = roleData.userRole=="DIS";
        	$scope.isRSM = roleData.customerClass=="RSM";
        	$scope.dealerId = roleData.sessionDetail.DealerID;
        	$scope.dealerName = roleData.sessionDetail.DealerName;
        	
        	// Get the attributes
        	appAttributeResource.get().then(function(attributesData) {

        		$scope.attributes = attributesData;
        		
        		menuResource.get().then(function(menuData) {
            		buildMenu(menuData);
            		});
        	});
        });
    	
    	
    	
    	function buildMenu(data) {
            var results = '<nav class="navbar navbar-default" role="navigation">\n';
            
            results += '<div class="navbar-header">\n';
            results += '<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#pga-navbar-collapse-id">\n';
            results += '<span class="sr-only">Toggle navigation</span>\n';
            results += '<span class="icon-bar"></span>\n<span class="icon-bar"></span>\n';
            results += '<span class="icon-bar"></span>\n</button>\n';
            results += '</div>';
            
            results += '<div class="collapse navbar-collapse" id="pga-navbar-collapse-id">\n';
            results += '<ul class="nav navbar-nav">\n';
            results += '<li><a href="' + data.data[0].homeLink +  '"><span class="glyphicon glyphicon-home"></span> Home</a></li>\n';
            var topItems = 0;
    
            /* Loop through each JSON element */
            $.each(data.data[0].category, function(i, obj) {
                var topMenu = '<li class="dropdown" id="nav_' + obj.displayName.replace(/ /g, '_') +'" style="position:relative">\n';
                topItems += 1;
                topMenu += '<a href="" class="dropdown-toggle" data-toggle="dropdown">';
                topMenu += obj.displayName + '<span class="caret"></span></a>\n';
                results += topMenu;
                
                if (obj.hasOwnProperty("menuLinks")) {
                    /* Now loop through the first-level submenu list  */
                    var subMenu = '<ul class="dropdown-menu">\n';
                    var doSub = 0;
                    $.each(obj.menuLinks, function(j, sub) {
                        if (sub.hasOwnProperty("subMenuLinks") && sub.subMenuLinks.length > 0) {
                            subMenu += '<li><a class="trigger right-caret">' + sub.displayName + '</a>\n';
                            subMenu += '<ul class="dropdown-menu sub-menu">\n';
    
                            /* This loops through the second-level submenu list */
                            $.each(sub.subMenuLinks, function(k, subsub) {
                                subMenu += '<li><a href="' + subsub.linkTarget + '">' + subsub.displayName + '</a></li>\n';
                            });
                            subMenu += '</ul>\n';
                            subMenu += '</li>\n';
    
                        } else {
                            subMenu += '<li id="nav_' + sub.displayName.replace(/ /g, '_') + '"><a href="' + sub.linkTarget + '">' + sub.displayName + '</a></li>\n';
                        }
                        doSub = 1;
                    });  /* Sub level menu items */ 
                    if (doSub) {
                        subMenu += '</ul>\n</li>\n';
                        results += subMenu;
                    }
                }
                
            }); /* Top level menu items */
            
            if (topItems >= 1) {
                results += '</ul>\n</div>\n</nav>\n';
            }
            
//                console.log("\n\n===== NavBar RESULTS ===== \n" + results);
            $("#topNavBarDiv").html(results);
            
            
            $(".dropdown-toggle").on("click",function(e){e.preventDefault();
            });
            
            
            $(".dropdown-menu > li > a.trigger").on("click",function(e){
                var current=$(this).next();
                var grandparent=$(this).parent().parent();
                grandparent.find(".sub-menu:visible").not(current).hide();
                if ((grandparent.offset().left + grandparent.width() + current.width()) > $(window).width()) {
                    // put the submenu on the left side if it would get clipped off
                    current.removeClass("sub-menu-right");
                    current.addClass("sub-menu-left");
                } else {
                    // put the submenu on the right side by default
                    current.removeClass("sub-menu-left");
                    current.addClass("sub-menu-right");
                }
                current.toggle();
                e.stopPropagation();
            });
            
            $(".dropdown-menu > li > a:not(.trigger)").on("click",function(){
                var root=$(this).closest('.dropdown');
                /* Removed to prevent arrow flipping
                root.find('.left-caret').toggleClass('right-caret left-caret');*/
                root.find('.sub-menu:visible').hide();
            });
        };    	
    	
    }

    commonHeader.CommonHeaderController = CommonHeaderController;
})();