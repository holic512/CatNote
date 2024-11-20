//
import {useRightSelectNodeId} from "../../Pinia/RightSelectNodeId.ts";
import {AddFolder} from "./AddFolder.ts";


export const onRightNFAddFolder = () => {
    const selectId = useRightSelectNodeId().GetSelectNodeId();
    AddFolder(selectId).then();
}