// fileHandlerConfig.ts 拖拽文件配置库
// 该文件定义了一个配置对象 fileHandlerConfig，用于处理在编辑器中的文件拖拽和粘贴行为。
// 主要支持图片类型文件的插入。

// 定义配置
import {saveCompressedNoteImage} from "../Main/Service/saveCompressedNoteImage";

const fileHandlerConfig: any = {

    // 允许的图片 MIME 类型，用于限制可插入的图片文件格式
    allowedMimeTypes: ['image/png', 'image/jpeg', 'image/gif', 'image/webp'],

    onDrop: (currentEditor: any, files: File[], pos: number) => {
        files.forEach(file => {
            const fileReader = new FileReader();

            // 将文件读取为 base64 格式的数据 URL
            fileReader.readAsDataURL(file);
            // 获取文件的 MIME 类型
            const fileType = file.type.split('/')[1];

            fileReader.onload = async () => {

                // 获取 base64
                const base64Image = fileReader.result;

                // 将 Base64 转换为 Blob（动态 MIME 类型）
                const blobImage = base64ToBlob(base64Image, file.type);

                const url = await saveCompressedNoteImage(blobImage, fileType)
                // 在指定位置插入图片并聚焦编辑器
                currentEditor.chain().insertContentAt(pos, {
                    type: 'image',
                    attrs: {src: url},
                }).focus().run();
            };
        });
    },


    onPaste: (currentEditor: any, files: File[], htmlContent: string, pos: number) => {
        files.forEach(file => {
            if (htmlContent) {
                // 如果粘贴内容包含 HTML，输出调试信息并停止手动插入
                console.log(htmlContent); // 调试输出
                return false;
            }

            const fileReader = new FileReader();
            // 将文件读取为 base64 格式的数据 URL
            fileReader.readAsDataURL(file);
            // 获取文件的 MIME 类型
            const fileType = file.type.split('/')[1];


            fileReader.onload = async () => {


                // 获取 base64
                const base64Image = fileReader.result;

                // 将 Base64 转换为 Blob（动态 MIME 类型）
                const blobImage = base64ToBlob(base64Image, file.type);

                const url = await saveCompressedNoteImage(blobImage, fileType)

                // 在光标位置插入图片并聚焦编辑器
                currentEditor.chain().insertContentAt(currentEditor.state.selection.anchor, {
                    type: 'image',
                    attrs: {src: url},
                }).focus().run();
            };
        });
    }
};

// 导出配置对象
export default fileHandlerConfig;


function base64ToBlob(base64: any, mimeType: any) {
    const byteCharacters = atob(base64.split(",")[1]);
    const byteNumbers = new Array(byteCharacters.length);
    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    return new Blob([byteArray], {type: mimeType});
}
