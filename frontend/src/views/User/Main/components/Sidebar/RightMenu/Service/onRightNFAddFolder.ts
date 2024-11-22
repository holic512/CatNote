//
import {useRightSelectNodeId} from "../../Pinia/RightSelectNodeId";
import {AddFolder} from "./AddFolder";


export const onRightNFAddFolder = () => {
    const selectId = useRightSelectNodeId().GetSelectNodeId();
    AddFolder(selectId).then();
}