<!--  用于 编辑内容 的页头  -->
<script setup lang="ts">

</script>

<template>
  <el-row justify="space-between">
    <el-col :span="4" class="el-col">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <el-text tag="b"> 日记</el-text>
        </el-breadcrumb-item>
        <el-breadcrumb-item>
          <el-text tag="b"> 一月一号</el-text>
        </el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>

    <el-col :span="20" class="el-col el-col-right">
      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 分享 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button">
          <el-icon color="#000000" size="18">
            <Position/>
          </el-icon>
        </el-button>

      </el-tooltip>

      <el-tooltip
          class="box-item"
          effect="dark"
          content=" 评论 "
          :show-after="500"
          placement="bottom"
      >

        <el-button text class="button"> <!--评论-->
          <el-icon color="#000000" size="18">
            <ChatLineSquare/>
          </el-icon>
        </el-button>

      </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="dark"
            content=" 历史版本 "
            :show-after="500"
            placement="bottom"
        >

          <el-button text class="button"> <!--历史版本-->
            <el-icon color="#000000" size="18">
              <Clock/>
            </el-icon>
          </el-button>

        </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="dark"
            content=" 收藏 "
            :show-after="500"
            placement="bottom"
        >

          <el-button text class="button">  <!--收藏-->
            <el-icon color="#000000" size="18">
              <Star/>
            </el-icon>
          </el-button>

        </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="dark"
            content=" 更多 "
            :show-after="500"
            placement="bottom"
        >

          <el-button text class="button"> <!--更多-->
            <el-icon color="#000000" size="18">
              <MoreFilled/>
            </el-icon>
          </el-button>

        </el-tooltip>

    </el-col>
  </el-row>
</template>

<style scoped>
.el-col {
  display: flex;
  align-items: center;
  height: 48px;
}

.el-col-right {
  justify-content: flex-end;
}

.button {
  width: 30px;
  height: 30px;
}

</style>