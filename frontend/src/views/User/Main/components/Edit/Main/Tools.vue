<script setup lang="ts">

import Bold from "@/views/User/Main/components/Edit/Main/icon/bold.vue";
import Italic from "@/views/User/Main/components/Edit/Main/icon/italic.vue";
import Underline from "@/views/User/Main/components/Edit/Main/icon/underline.vue";
import Strikethrough from "@/views/User/Main/components/Edit/Main/icon/strikethrough.vue";
import UnorderedList from "@/views/User/Main/components/Edit/Main/icon/unorderedList.vue";
import OrderedList from "@/views/User/Main/components/Edit/Main/icon/orderedList.vue";
</script>

<template>
  <div class="container">

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 撤销 "
        :show-after="500"
        placement="bottom"
    >

      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Back/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 重做 "
        :show-after="500"
        placement="bottom"
    >

      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Right/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 格式刷 "
        :show-after="500"
        placement="bottom"
    >

      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Brush/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-divider direction="vertical"/>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 插入 "
        :show-after="500"
        placement="bottom"
    >

      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Plus/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-divider direction="vertical"/>

    <el-select v-model="value" style="width: 120px;" size="small">
      <el-option
          label="正文"
      />
    </el-select>

    <el-select v-model="value" style="width: 48px;" size="small">
      <el-option
          label="字号"
      />
    </el-select>

    <el-divider direction="vertical"/>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 加粗 "
        :show-after="500"
        placement="bottom"
    >

      <!--加粗-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Bold/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 斜体 "
        :show-after="500"
        placement="bottom"
    >

      <!--斜体-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <Italic/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 下划线 "
        :show-after="500"
        placement="bottom"
    >

      <!--下划线-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <underline/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 删除线 "
        :show-after="500"
        placement="bottom"
    >

      <!--删除线-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <strikethrough/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 颜色 "
        :show-after="500"
        placement="bottom"
    >

      <!--颜色-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          1
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 高亮 "
        :show-after="500"
        placement="bottom"
    >

      <!--高亮-->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          2
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-divider direction="vertical"/>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 无序排列 "
        :show-after="500"
        placement="bottom"
    >

      <!--  无序排列  -->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <OrderedList/>
        </el-icon>
      </el-button>

    </el-tooltip>

    <el-tooltip
        class="box-item"
        effect="dark"
        content=" 有序排列 "
        :show-after="500"
        placement="bottom"
    >

      <!--  有序排列  -->
      <el-button text class="button">
        <el-icon color="#000000" size="18">
          <UnorderedList/>
        </el-icon>
      </el-button>

    </el-tooltip>

  </div>

</template>

<style scoped>
.container {
  width: 100%;
  height: 44px;
  border-bottom: 1px solid #F0F3F5;

  display: flex;
  align-items: center;

  padding-left: 12px;
}

.button {
  width: 30px;
  height: 30px;
}
</style>