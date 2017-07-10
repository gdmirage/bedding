<template>
  <section class="db">
    <template v-if="!$route.meta.hidden">
      <!-- header start  -->
      <header class="db-header">
        <router-link class="logo" :to="{path: '/'}">Lovego</router-link>
        <div class="user-info" v-if="user.id">
          <span v-text="user.username"></span>
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              <img :src="user.avatar">
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </header>
      <!-- header end  -->

      <!-- body start  -->
      <div class="db-body">

        <!-- menu start -->
        <aside class="db-menu-wrapper">

          <el-menu :default-active="activeMenu" class="db-menu-bar" router>
            <template v-for="(route, index) in $router.options.routes[$router.options.routes.length - 2].children">
              <template v-if="route.children && route.name">
                <el-submenu :index="route.name">
                  	<template slot="title"><i :class="route.iconClass"></i>{{route.name}}</template>
                  	<template v-for="(cRoute, cIndex) in route.children">
					  <template v-if="cRoute.children && cRoute.name">
					    <el-submenu :index="cRoute.name">
					      <template slot="title"><i :class="cRoute.iconClass"></i>{{cRoute.name}}</template>
					      <el-menu-item :index="dRoute.name" v-for="(dRoute, dIndex) in cRoute.children" :route="dRoute">{{dRoute.name}}</el-menu-item>
					    </el-submenu>
					  </template>
					  <template v-if="!cRoute.children && cRoute.name">
					    <el-menu-item :index="cRoute.name" :route="cRoute">{{cRoute.name}}</el-menu-item>
					  </template>
                  	</template>
                </el-submenu>
              </template>
              <template v-if="!route.children && route.name">
                <el-menu-item :index="route.name" :route="route"><i :class="route.iconClass"></i>{{route.name}}</el-menu-item>
              </template>
            </template>
          </el-menu>
        </aside>
        <!-- menu end -->

        <!-- content start -->
        <div class="db-content-wrapper">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>位置：</el-breadcrumb-item>
                <el-breadcrumb-item
                    v-for="item in breadcrumbs"
                    >{{item.name}}
                </el-breadcrumb-item>
            </el-breadcrumb>
            <section class="db-content">
                <router-view></router-view>
            </section>
        </div>
        <!-- content end -->
      </div>
      <!-- body end  -->
</template>
<template v-else>
  <router-view></router-view>
</template>
  </section>


</template>

<script>
export default {
  data() {
    return {
      user: {
        id: '1',
        username: 'test',
        avatar: 'https://o0p2nwku4.qnssl.com/favicon.ico'
      },
      activeMenu: '',
        breadcrumbs: null
    };
  },
  created() {
    this.activeMenu = this.$route.name;
  },
  watch: {
    '$route'(to, from) {
        console.log(this.$route);
        this.activeMenu = this.$route.name;
        this.breadcrumbs = (this.$parent && this.$parent.$route && this.$parent.$route.matched) || [];
    }
  },
  methods: {
    logout() {
      this.$confirm('确定要注销吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        this.$alert("测试")
      }).catch(() => {});
    }
  }
};
</script>

<style lang="scss">
  $dark-gray: #35405b;

  html{
    height: 100%;
    margin: 0;
    font-size: 62.5%;
  }

  body {
    font-size: 1.4rem;
    padding: 0;
    margin: 0;
    font-family: Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif;
    font-weight: 400;
    -webkit-font-smoothing: antialiased;
  }

  a {
    text-decoration: none;
    color: inherit;
  }

  .el-loading-mask {
    z-index: 1000;
  }
  .filters {
    /*margin: 0;*/
    border: 1px #efefef solid;
    padding: 10px;
    background: #f9f9f9;

    .filter {
      display: inline-block;
      width: auto;
      padding: 10px;
      border-radius: 5px;
      .el-select {
        display: inline-block;
      }
    }

    .el-input {
      width: 150px;
      display: inline-block;
    }
  }
  .el-breadcrumb {
      margin: 20px 0px 20px 30px;
  }


.db {
  .el-dropdown-menu {
    margin-top: 20px;
  }
  // header
  .db-header {
    width: 100%;
    height: 60px;
    background: #20A0FF;
    padding: 13px 20px;
    box-sizing: border-box;
    color: #ffffff;
    z-index: 99;
    position: fixed;
    left: 0;
    top: 0;

    .logo{
      font-size: 2.4rem;
    }

    .user-info {
      float: right;

      img {
        width: 25px;
        height: 25px;
        vertical-align: -7px;
        margin: 0 0 0 10px;
        cursor: pointer;
      }
    }
  }

  // body
  .db-body {

    // menu
    .db-menu-wrapper {
      position: fixed;
      left: 0;
      top: 60px;
      background: red;
      height: 100%;
      overflow: auto;
      z-index: 98;

      .db-menu-bar {
        height: 100%;
        flex-grow: 0;
        width: 200px;
      }
    }

    // content
    .db-content-wrapper {
      width: 100%;
      z-index: 97;
      box-sizing: border-box;
      padding: 60px 0px 0px 200px;

      .db-content {
        padding: 20px;

        .db-content-inner {
          padding: 30px 0px;
        }
      }
    }
  }
}
</style>
