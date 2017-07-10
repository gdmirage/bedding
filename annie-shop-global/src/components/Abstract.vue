<template lang="html">
  <div class="">
    <router-view></router-view>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                children: null,
                breadcrumbs: null
            };
        },
        watch: {
            '$route'(to, from) {
                this.breadcrumbs = (this.$parent && this.$parent.$route && this.$parent.$route.matched) || [];
            }
        },
        mounted() {
            this.breadcrumbs = (this.$parent && this.$parent.$route && this.$parent.$route.matched) || [];
        }

    };
</script>

<style lang="scss">

  .abstract {
    .card-wrapper {
      display: flex;
      flex-direction: column;
      .img {
        height: 150px;
      }
      .text {
        display: flex;
        justify-content: space-around;
        align-items: center;
        a {
          color: #20a0ff;
        }
      }
      img {
        width: 100%;
        height: 150px;
      }
    }
  }
</style>
